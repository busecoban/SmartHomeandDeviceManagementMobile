using System;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllDeviceById
{
    public class GetDeviceByIdQuery : IRequest<Response<Device>>
    {
        public int Id { get; set; }
        public class GetDeviceIdQueryHandler : IRequestHandler<GetDeviceByIdQuery, Response<Device>>
        {
            private readonly IDeviceRepositoryAsync _deviceRepository;
            public GetDeviceIdQueryHandler(IDeviceRepositoryAsync deviceRepository)
            {
                _deviceRepository = deviceRepository;
            }

            public async Task<Response<Device>> Handle(GetDeviceByIdQuery request, CancellationToken cancellationToken)
            {
                var device = await _deviceRepository.GetByIdAsync(request.Id);
                if (device == null) throw new ApiException($"Device not found.");
                return new Response<Device>(device);
            }
        }
    }
}
