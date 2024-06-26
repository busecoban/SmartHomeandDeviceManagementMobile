using System;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllDevices
{
    public class GetAllDevicesQuery : IRequest<PagedResponse<IEnumerable<GetAllDeviceViewModel>>>
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; }
    }
    public class GetAllDevicesQueryHandler : IRequestHandler<GetAllDevicesQuery, PagedResponse<IEnumerable<GetAllDeviceViewModel>>>
    {
        private readonly IDeviceRepositoryAsync _deviceRepository;
        public GetAllDevicesQueryHandler(IDeviceRepositoryAsync deviceRepository)
        {
            _deviceRepository = deviceRepository;
        }

        public Task<PagedResponse<IEnumerable<GetAllDeviceViewModel>>> Handle(GetAllDevicesQuery request, CancellationToken cancellationToken)
        {
            var validfilter = new GetAllDeviceParameter
            {
                PageNumber = request.PageNumber,
                PageSize = request.PageSize
            };
            return _deviceRepository.GetAllDevicesAsync(validfilter);
        }
    }
}

