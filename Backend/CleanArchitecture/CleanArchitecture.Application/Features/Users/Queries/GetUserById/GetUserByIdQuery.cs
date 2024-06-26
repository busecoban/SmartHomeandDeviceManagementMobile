using System;
using System.Threading;
using System.Threading.Tasks;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;

namespace CleanArchitecture.Core.Features.Devices.Queries.GetUserById
{
	public class GetUserByIdQuery : IRequest<Response<User>>
	{
		public int Id { get; set; }
		public class GetUserIdQueryHandler : IRequestHandler<GetUserByIdQuery, Response<User>>
		{
			private readonly IUserRepositoryAsync _userRepository;
			public GetUserIdQueryHandler(IUserRepositoryAsync userRepository)
			{
				_userRepository = userRepository;
			}

            public async Task<Response<User>> Handle(GetUserByIdQuery request, CancellationToken cancellationToken)
            {
				var user = await _userRepository.GetByIdAsync(request.Id);
				if (user == null) throw new ApiException($"User not found.");
				return new Response<User>(user);
            }
        }
	}
}

