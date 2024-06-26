using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
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
    public class UserRepositoryAsync : GenericRepositoryAsync<User>, IUserRepositoryAsync
    {
        private readonly DbSet<User> _users;
        private readonly ApplicationDbContext _dbContext;

        public UserRepositoryAsync(ApplicationDbContext dbContext) : base(dbContext)
        {
            _dbContext = dbContext;
            _users = dbContext.Set<User>();
        }

        public  async Task<PagedResponse<IEnumerable<GetAllUsersViewModel>>> GetAllUsersAsync(GetAllUserParameter validfilter)
        {
            IQueryable<User> users = _users.Include(p => p.Homes).AsQueryable();
            var totalcount = users.Count();
            if (totalcount == 0)
            {
                throw new Exception();
            }
            users = users.Skip((validfilter.PageNumber - 1) * validfilter.PageSize).Take(validfilter.PageSize);
            var result = await users.Select(p => new GetAllUsersViewModel
            {
                Id = p.Id,
                Name = p.Name,
                Email = p.Email,
                Password = p.Password,
                HomeId = p.HomeID,
                //homes = p.Homes.ToList()
            }).ToListAsync();
            return new PagedResponse<IEnumerable<GetAllUsersViewModel>>(result, validfilter.PageNumber, validfilter.PageSize);
        }
    }
}
