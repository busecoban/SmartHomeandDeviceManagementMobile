using System;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Homes.Queries.GetAllHomesByOwnerId
{
    public class GetAllHomesByOwnerIdQuery : IRequest<PagedResponse<IEnumerable<GetAllHomeByOwnerIdViewModel>>>
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; }
        public string OwnerId { get; set; }
    }

    public class GetAllHomesByOwnerIdQueryHandler : IRequestHandler<GetAllHomesByOwnerIdQuery, PagedResponse<IEnumerable<GetAllHomeByOwnerIdViewModel>>>
    {
        private readonly IHomeRepositoryAsync _homeRepository;

        public GetAllHomesByOwnerIdQueryHandler(IHomeRepositoryAsync homeRepository)
        {
            _homeRepository = homeRepository;
        }

        public async Task<PagedResponse<IEnumerable<GetAllHomeByOwnerIdViewModel>>> Handle(GetAllHomesByOwnerIdQuery request, CancellationToken cancellationToken)
        {
            var validFilter = new GetAllHomeByOwnerIdParameter
            {
                PageNumber = request.PageNumber,
                PageSize = request.PageSize,
                OwnerId = request.OwnerId
            };

            return await _homeRepository.GetAllHomesByOwnerIdAsync(validFilter);
        }
    }
}

