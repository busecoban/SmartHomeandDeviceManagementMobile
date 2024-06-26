using System;
using CleanArchitecture.Core.Features.Rooms.Queries.GetAllRooms;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Rooms.Queries.GetAllRoomsByHomeId
{
    public class GetAllRoomsByHomeIdQuery : IRequest<PagedResponse<IEnumerable<GetAllRoomsByHomeIdViewModel>>>
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; }
        public int HomeId { get; set; }

    }

    public class GetAllRoomsByHomeIdQueryHandler : IRequestHandler<GetAllRoomsByHomeIdQuery, PagedResponse<IEnumerable<GetAllRoomsByHomeIdViewModel>>>
    {
        private readonly IRoomRepositoryAsync _roomRepository;
        public GetAllRoomsByHomeIdQueryHandler(IRoomRepositoryAsync roomRepository)
        {
            _roomRepository = roomRepository;
        }

        public async Task<PagedResponse<IEnumerable<GetAllRoomsByHomeIdViewModel>>> Handle(GetAllRoomsByHomeIdQuery request, CancellationToken cancellationToken)
        {
            var validfilter = new GetAllRoomsByHomeIdParameter
            {
                PageSize = request.PageSize,
                PageNumber = request.PageNumber,
                HomeId = request.HomeId
            };
            return await _roomRepository.GetAllRoomsByHomeIdAsync(validfilter);


        }
    }
}

