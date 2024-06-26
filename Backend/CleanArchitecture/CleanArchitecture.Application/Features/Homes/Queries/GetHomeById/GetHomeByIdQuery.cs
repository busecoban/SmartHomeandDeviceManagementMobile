using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using CleanArchitecture.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Products.Queries.GetHomeById
{
    public class GetHomeByIdQuery : IRequest<Response<Home>>
    {
        public int Id { get; set; }

        public class GetHomeByIdQueryHandler : IRequestHandler<GetHomeByIdQuery, Response<Home>>
        {
            private readonly IHomeRepositoryAsync _homeRepository;

            public GetHomeByIdQueryHandler(IHomeRepositoryAsync homeRepository)
            {
                _homeRepository = homeRepository;
            }

            public async Task<Response<Home>> Handle(GetHomeByIdQuery query, CancellationToken cancellationToken)
            {
                var home = await _homeRepository.GetByIdAsync(query.Id);
                if (home == null) throw new ApiException($"Home Not Found.");

                // Load the rooms if they are not already included
                home = await _homeRepository.GetByIdAsync(home.Id);
                return new Response<Home>(home);
            }
        }
    }
}
