using System;
using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Devices.Commands.DeleteDeviceById
{
    public class DeleteDeviceByIdCommand : IRequest<Response<int>>
    {
        public int Id { get; set; }
        public class DeleteDeviceByIdCommandHandler : IRequestHandler<DeleteDeviceByIdCommand, Response<int>>
        {
            private readonly IDeviceRepositoryAsync _deviceRepository;
            public DeleteDeviceByIdCommandHandler(IDeviceRepositoryAsync deviceRepository)
            {
                _deviceRepository = deviceRepository;
            }
            public async Task<Response<int>> Handle(DeleteDeviceByIdCommand request, CancellationToken cancellationToken)
            {
                var device = await _deviceRepository.GetByIdAsync(request.Id);
                if (device == null) throw new ApiException($"Device Not Found.");
                await _deviceRepository.DeleteAsync(device);
                return new Response<int>(device.Id);
            }
        }

    }
}