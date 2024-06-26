using System;
using CleanArchitecture.Core.Features.Products.Queries.GetAllProducts;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Collections.Generic;
using AutoMapper;
using System.Threading;
using CleanArchitecture.Core.Interfaces.Repositories;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Rooms.Queries.GetAllRooms
{


    public class GetAllRoomsQuery : IRequest<PagedResponse<IEnumerable<GetAllRoomsViewModel>>>
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; }

    }

    public class GetAllRoomsQueryHandler : IRequestHandler<GetAllRoomsQuery, PagedResponse<IEnumerable<GetAllRoomsViewModel>>>
    {
        private readonly IRoomRepositoryAsync _roomRepository;
        public GetAllRoomsQueryHandler(IRoomRepositoryAsync roomRepository)
        {
            _roomRepository = roomRepository;
        }

        public async Task<PagedResponse<IEnumerable<GetAllRoomsViewModel>>> Handle(GetAllRoomsQuery request, CancellationToken cancellationToken)
        {
            var validfilter = new GetAllRoomParameter
            {
                PageSize = request.PageSize,
                PageNumber = request.PageNumber,
            };
            return await _roomRepository.GetAllRoomsAsync(validfilter);


        }
    }
}

