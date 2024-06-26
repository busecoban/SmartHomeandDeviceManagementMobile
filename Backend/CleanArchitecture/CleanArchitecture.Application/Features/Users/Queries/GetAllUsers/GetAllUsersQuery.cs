using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;

namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers
{
	public class GetAllUsersQuery : IRequest<PagedResponse<IEnumerable<GetAllUsersViewModel>>>{
		public int PageNumber { get; set; }
		public int PageSize { get; set; }
	}
	public class GetAllUsersQueryHandler : IRequestHandler<GetAllUsersQuery, PagedResponse<IEnumerable<GetAllUsersViewModel>>>
	{
		private readonly IUserRepositoryAsync _userRepository;
		public GetAllUsersQueryHandler(IUserRepositoryAsync userRepository)
		{
			_userRepository = userRepository;
		}

        public Task<PagedResponse<IEnumerable<GetAllUsersViewModel>>> Handle(GetAllUsersQuery request, CancellationToken cancellationToken)
        {
			var validfilter = new GetAllUserParameter
			{
				PageNumber = request.PageNumber,
				PageSize = request.PageSize
			};
		return _userRepository.GetAllUsersAsync(validfilter);
        }
    }
}

