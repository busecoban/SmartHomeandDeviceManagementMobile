using System;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomesByOwnerId;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllDevicesByRoomId
{
    public class GetAllDevicesByRoomIdQuery : IRequest<PagedResponse<IEnumerable<GetAllDevicesByRoomIdViewModel>>>
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; }
        public int RoomId { get; set; }
    }

    public class GetAllDevicesByRoomIdQueryHandler : IRequestHandler<GetAllDevicesByRoomIdQuery, PagedResponse<IEnumerable<GetAllDevicesByRoomIdViewModel>>>
    {
        private readonly IDeviceRepositoryAsync _deviceRepository;

        public GetAllDevicesByRoomIdQueryHandler(IDeviceRepositoryAsync deviceRepository)
        {
            _deviceRepository = deviceRepository;
        }

        public async Task<PagedResponse<IEnumerable<GetAllDevicesByRoomIdViewModel>>> Handle(GetAllDevicesByRoomIdQuery request, CancellationToken cancellationToken)
        {
            var validFilter = new GetAllDevicesByRoomIdParameter
            {
                PageNumber = request.PageNumber,
                PageSize = request.PageSize,
                RoomId = request.RoomId
            };

            return await _deviceRepository.GetAllDevicesByRoomIdAsync(validFilter);
        }
    }
}
