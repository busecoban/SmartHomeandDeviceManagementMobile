using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers;
using CleanArchitecture.Core.Wrappers;

namespace CleanArchitecture.Core.Interfaces.Repositories
{
    public interface IUserRepositoryAsync : IGenericRepositoryAsync<User>
    {
        Task<PagedResponse<IEnumerable<GetAllUsersViewModel>>> GetAllUsersAsync(GetAllUserParameter validfilter);
    }
}
