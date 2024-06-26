using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomesByOwnerId;
using CleanArchitecture.Core.Wrappers;

namespace CleanArchitecture.Core.Interfaces.Repositories
{
    public interface IHomeRepositoryAsync : IGenericRepositoryAsync<Home>
    {
        public Task<PagedResponse<IEnumerable<GetAllHomesViewModel>>> GetAllHomesAsync(GetAllHomeParameter request);
        public Task<PagedResponse<IEnumerable<GetAllHomeByOwnerIdViewModel>>> GetAllHomesByOwnerIdAsync(GetAllHomeByOwnerIdParameter request);
    }
}