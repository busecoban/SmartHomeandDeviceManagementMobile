using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
using CleanArchitecture.Core.Features.Rooms.Queries.GetAllRooms;
using CleanArchitecture.Core.Features.Rooms.Queries.GetAllRoomsByHomeId;
using CleanArchitecture.Core.Wrappers;

namespace CleanArchitecture.Core.Interfaces.Repositories
{
    public interface IRoomRepositoryAsync : IGenericRepositoryAsync<Room>
    {
        public Task<PagedResponse<IEnumerable<GetAllRoomsViewModel>>> GetAllRoomsAsync(GetAllRoomParameter request);
        public Task<Home> GetHomeByIdAsync(int homeId);
        public Task AddRoomToHome(Room room, int homeId);
        public Task<PagedResponse<IEnumerable<GetAllRoomsByHomeIdViewModel>>> GetAllRoomsByHomeIdAsync(GetAllRoomsByHomeIdParameter request);



    }
}
