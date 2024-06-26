using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
using CleanArchitecture.Core.Features.Rooms.Queries.GetAllRooms;
using CleanArchitecture.Core.Features.Rooms.Queries.GetAllRoomsByHomeId;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using CleanArchitecture.Infrastructure.Contexts;
using CleanArchitecture.Infrastructure.Repository;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CleanArchitecture.Infrastructure.Repositories
{
    public class RoomRepositoryAsync : GenericRepositoryAsync<Room>, IRoomRepositoryAsync
    {
        private readonly DbSet<Room> _rooms;
        private readonly ApplicationDbContext _dbContext;

        public RoomRepositoryAsync(ApplicationDbContext dbContext) : base(dbContext)
        {
            _dbContext = dbContext;
            _rooms = dbContext.Set<Room>();
        }

        public async Task<PagedResponse<IEnumerable<GetAllRoomsViewModel>>> GetAllRoomsAsync(GetAllRoomParameter request)
        {
            if (request == null) throw new ArgumentNullException(nameof(request));

            IQueryable<Room> rooms = _rooms.Include(p => p.Devices).Include(s=>s.Home).AsQueryable();
            var totalCount = await rooms.CountAsync();
            if (totalCount == 0)
            {
                throw new InvalidOperationException("Hiç oda bulunamadı.");
            }
            rooms = rooms.Skip((request.PageNumber - 1) * request.PageSize).Take(request.PageSize);
            var result = await rooms.Select(p => new GetAllRoomsViewModel
            {
                Id = p.Id,
                Name = p.Name,
                HomeId = p.Home.Name
            }).ToListAsync();

            return new PagedResponse<IEnumerable<GetAllRoomsViewModel>>(result, request.PageNumber, request.PageSize);
        }

        public async Task<Home> GetHomeByIdAsync(int homeId)
        {
            return await _dbContext.Homes.Include(h => h.Rooms).FirstOrDefaultAsync(h => h.Id == homeId);
        }

        public async Task AddRoomToHome(Room room, int homeId)
        {
            if (room == null) throw new ArgumentNullException(nameof(room));

            var home = await GetHomeByIdAsync(homeId);
            if (home == null)
            {
                throw new InvalidOperationException("Uygun bir ev seçiniz.");
            }
            home.Rooms.Add(room);
            await _dbContext.SaveChangesAsync();
        }

        public async Task<PagedResponse<IEnumerable<GetAllRoomsByHomeIdViewModel>>> GetAllRoomsByHomeIdAsync(GetAllRoomsByHomeIdParameter request)
        {
            if (request == null) throw new ArgumentNullException(nameof(request));

            IQueryable<Room> rooms = _rooms.AsQueryable();
            if (request.HomeId != 0)
            {
                rooms = _rooms.Where(h => h.HomeID == request.HomeId);
            }
            var totalCount = await rooms.CountAsync();
            if (totalCount == 0)
            {
                throw new InvalidOperationException("Hiç oda bulunamadı.");
            }
            rooms = rooms.Skip((request.PageNumber - 1) * request.PageSize).Take(request.PageSize);
            var result = await rooms.Select(p => new GetAllRoomsByHomeIdViewModel
            {
                Id = p.Id,
                Name = p.Name,
                HomeID = p.HomeID
            }).ToListAsync();

            return new PagedResponse<IEnumerable<GetAllRoomsByHomeIdViewModel>>(result, request.PageNumber, request.PageSize);
        }
    }
}
