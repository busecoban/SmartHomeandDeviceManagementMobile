using System;
using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Devices.Commands.UpdateDevice
{
    public class UpdateDeviceCommand : IRequest<Response<int>>
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public bool Status { get; set; }
    }
    public class UpdateDeviceCommandHandler : IRequestHandler<UpdateDeviceCommand, Response<int>>
    {
        private readonly IDeviceRepositoryAsync _deviceRepository;
        public UpdateDeviceCommandHandler(IDeviceRepositoryAsync deviceRepository)
        {
            _deviceRepository = deviceRepository;
        }
        public async Task<Response<int>> Handle(UpdateDeviceCommand request, CancellationToken cancellationToken)
        {
            var device = await _deviceRepository.GetByIdAsync(request.Id);

            if (device == null) throw new EntityNotFoundException("device", request.Id);

            device.Id = request.Id;
            device.Name = request.Name;
            device.Status = request.Status;
            await _deviceRepository.UpdateAsync(device);
            return new Response<int>(device.Id);
        }
    }
}
