using System;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Interfaces.Repositories;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Devices.Commands.CreateUser
{
    public class CreateDeviceCommand : IRequest<int>
    {
        public string Name { get; set; }
        public string Type { get; set; }
        public bool Status { get; set; }
        public int RoomId { get; set; }
    }

    public class CreateDeviceCommandHandler : IRequestHandler<CreateDeviceCommand, int>
    {
        private readonly IDeviceRepositoryAsync _deviceRepository;


        public CreateDeviceCommandHandler(IDeviceRepositoryAsync deviceRepository)
        {
            _deviceRepository = deviceRepository;
        }

        public async Task<int> Handle(CreateDeviceCommand request, CancellationToken cancellationToken)
        {
            var device = new Device
            {
                Name = request.Name,
                Type = request.Type,
                RoomID = request.RoomId,
                Status = request.Status
            };
            await _deviceRepository.AddAsync(device);
            return device.Id;
        }
    }
}

