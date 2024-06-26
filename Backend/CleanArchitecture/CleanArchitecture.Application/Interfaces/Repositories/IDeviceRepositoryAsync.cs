using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllDevices;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllDevicesByRoomId;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers;
using CleanArchitecture.Core.Wrappers;

namespace CleanArchitecture.Core.Interfaces.Repositories
{
    public interface IDeviceRepositoryAsync : IGenericRepositoryAsync<Device>
    {
        Task<PagedResponse<IEnumerable<GetAllDeviceViewModel>>> GetAllDevicesAsync(GetAllDeviceParameter validfilter);
        Task<PagedResponse<IEnumerable<GetAllDevicesByRoomIdViewModel>>> GetAllDevicesByRoomIdAsync(GetAllDevicesByRoomIdParameter validFilter);
        public Task AddDevicesToRoom(Device device, int roomId);
    }
}
