using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllDevices;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllDevicesByRoomId;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomesByOwnerId;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using CleanArchitecture.Infrastructure.Contexts;
using CleanArchitecture.Infrastructure.Repository;
using Microsoft.EntityFrameworkCore;
using Org.BouncyCastle.Asn1.Ocsp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CleanArchitecture.Infrastructure.Repositories
{
    public class DeviceRepositoryAsync : GenericRepositoryAsync<Device>, IDeviceRepositoryAsync
    {
        private readonly DbSet<Device> _devices;
        private readonly ApplicationDbContext _dbContext;

        public DeviceRepositoryAsync(ApplicationDbContext dbContext) : base(dbContext)
        {
            _dbContext = dbContext;
            _devices = dbContext.Set<Device>();
        }

        public async Task<PagedResponse<IEnumerable<GetAllDeviceViewModel>>> GetAllDevicesAsync(GetAllDeviceParameter validfilter)
        {
            IQueryable<Device> devices = _devices.AsQueryable();
            var totalcount = devices.Count();
            if (totalcount == 0)
            {
                throw new Exception();
            }
            devices = devices.Skip((validfilter.PageNumber - 1) * validfilter.PageSize).Take(validfilter.PageSize);
            var result = await devices.Select(p => new GetAllDeviceViewModel
            {
                Id = p.Id,
                Name = p.Name,
                Status = p.Status,
                Type = p.Type,
                RoomId = p.RoomID

            }).ToListAsync();
            return new PagedResponse<IEnumerable<GetAllDeviceViewModel>>(result, validfilter.PageNumber, validfilter.PageSize);
        }

        public async Task<Room> GetRoomByIdAsync(int roomId)
        {
            return await _dbContext.Rooms.Include(h => h.Devices).FirstOrDefaultAsync(h => h.Id == roomId);
        }
        
        public async Task<PagedResponse<IEnumerable<GetAllDevicesByRoomIdViewModel>>> GetAllDevicesByRoomIdAsync(GetAllDevicesByRoomIdParameter request)
        {
            if (request == null) throw new ArgumentNullException(nameof(request));

            IQueryable<Device> devices = _devices.AsQueryable();
            if (request.RoomId != 0)
            {
                devices = _devices.Where(h => h.RoomID == request.RoomId).AsQueryable();
            }

            var totalCount = await devices.CountAsync();
            if (totalCount == 0)
            {
                throw new InvalidOperationException("No Room found.");
            }
            var pagedHomes = await devices
            .Skip((request.PageNumber - 1) * request.PageSize)
                .Take(request.PageSize)
                .Select(p => new GetAllDevicesByRoomIdViewModel
                {
                    Id = p.Id,
                    Name = p.Name,
                    Type = p.Type,
                    RoomID = p.RoomID,
                    Status = p.Status
                })
            .ToListAsync();
            return new PagedResponse<IEnumerable<GetAllDevicesByRoomIdViewModel>>(pagedHomes, request.PageNumber > 0 ? request.PageNumber : 1, request.PageSize > 0 ? request.PageSize : 10);

        }

        public async Task AddDevicesToRoom(Device device, int roomId)
        {
            if (device == null) throw new ArgumentNullException(nameof(device));

            var room = await GetRoomByIdAsync(roomId);
            if (room == null)
            {
                throw new InvalidOperationException("Uygun bir room seçiniz.");
            }
            room.Devices.Add(device);
            await _dbContext.SaveChangesAsync();
        }
    }
}