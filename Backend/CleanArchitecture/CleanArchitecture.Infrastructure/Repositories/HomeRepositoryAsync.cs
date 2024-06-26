using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
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
    public class HomeRepositoryAsync : GenericRepositoryAsync<Home>, IHomeRepositoryAsync
    {
        private readonly DbSet<Home> _homes;
        private readonly ApplicationDbContext _dbContext;

        public HomeRepositoryAsync(ApplicationDbContext dbContext) : base(dbContext)
        {
            _dbContext = dbContext;
            _homes = dbContext.Set<Home>();
        }

        public async Task<PagedResponse<IEnumerable<GetAllHomesViewModel>>> GetAllHomesAsync(GetAllHomeParameter request)
        {
            if (request == null) throw new ArgumentNullException(nameof(request));

            IQueryable<Home> homes = _homes.AsQueryable();
            

            var totalCount = await homes.CountAsync();
            if (totalCount == 0)
            {
                throw new InvalidOperationException("No homes found.");
            }

            var pagedHomes = await homes
                .Skip((request.PageNumber - 1) * request.PageSize)
                .Take(request.PageSize)
                .Select(p => new GetAllHomesViewModel
                {
                    Id = p.Id,
                    Name = p.Name,
                    Address = p.Address,
                    RoomNames = p.Rooms.Select(r => r.Name).ToList()
                })
                .ToListAsync();
            return new PagedResponse<IEnumerable<GetAllHomesViewModel>>(pagedHomes, request.PageNumber >0 ? request.PageNumber:1, request.PageSize > 0 ? request.PageSize : 10);
        }

        public async Task<PagedResponse<IEnumerable<GetAllHomeByOwnerIdViewModel>>> GetAllHomesByOwnerIdAsync(GetAllHomeByOwnerIdParameter request)
        {
            if (request == null) throw new ArgumentNullException(nameof(request));

            IQueryable<Home> homes = _homes.AsQueryable();
            if (request.OwnerId != "")
            {
                homes = _homes.Where(h => h.OwnerId == request.OwnerId).AsQueryable();
            }

            var totalCount = await homes.CountAsync();
            if (totalCount == 0)
            {
                throw new InvalidOperationException("No homes found.");
            }
            var pagedHomes = await homes
            .Skip((request.PageNumber - 1) * request.PageSize)
                .Take(request.PageSize)
                .Select(p => new GetAllHomeByOwnerIdViewModel
                {
                    Id = p.Id,
                    Name = p.Name,
                    Address = p.Address,
                    OwnerId = p.OwnerId,
                    RoomNames = p.Rooms.Select(r => r.Name).ToList()
                })
            .ToListAsync();
            return new PagedResponse<IEnumerable<GetAllHomeByOwnerIdViewModel>>(pagedHomes, request.PageNumber > 0 ? request.PageNumber : 1, request.PageSize > 0 ? request.PageSize : 10);

        }
    }
}
