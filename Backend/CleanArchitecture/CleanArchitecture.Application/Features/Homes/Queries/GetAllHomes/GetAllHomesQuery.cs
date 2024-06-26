using System.Collections.Generic;
using CleanArchitecture.Core.Interfaces.Repositories;
using System.Threading;
using System.Threading.Tasks;
using CleanArchitecture.Core.Wrappers;
using MediatR;

namespace CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes
{
    public class GetAllHomesQuery : IRequest<PagedResponse<IEnumerable<GetAllHomesViewModel>>>
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; }
    }

    public class GetAllHomesQueryHandler : IRequestHandler<GetAllHomesQuery, PagedResponse<IEnumerable<GetAllHomesViewModel>>>
    {
        private readonly IHomeRepositoryAsync _homeRepository;

        public GetAllHomesQueryHandler(IHomeRepositoryAsync homeRepository)
        {
            _homeRepository = homeRepository;
        }

        public async Task<PagedResponse<IEnumerable<GetAllHomesViewModel>>> Handle(GetAllHomesQuery request, CancellationToken cancellationToken)
        {
            var validFilter = new GetAllHomeParameter
            {
                PageNumber = request.PageNumber,
                PageSize = request.PageSize
            };

            return await _homeRepository.GetAllHomesAsync(validFilter);
        }
    }
}
