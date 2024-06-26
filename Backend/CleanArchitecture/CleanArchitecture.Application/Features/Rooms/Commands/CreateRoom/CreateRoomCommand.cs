using System.Threading;
using System.Threading.Tasks;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Interfaces.Repositories;
using MediatR;

namespace CleanArchitecture.Core.Features.Rooms.Commands.CreateRoom
{
    public class CreateRoomCommand : IRequest<int>
    {
        public string Name { get; set; }
        public int HomeID { get; set; }
        public Home Home { get; set; }
    }

    public class CreateRoomCommandHandler : IRequestHandler<CreateRoomCommand, int>
    {
        private readonly IRoomRepositoryAsync _roomRepository;

        public CreateRoomCommandHandler(IRoomRepositoryAsync roomRepository)
        {
            _roomRepository = roomRepository;
        }

        public async Task<int> Handle(CreateRoomCommand request, CancellationToken cancellationToken)
        {
            var room = new Entities.Room
            {
                Name = request.Name,
                HomeID = request.HomeID,
                Home = request.Home
            };
            await _roomRepository.AddRoomToHome(room, request.HomeID); // This will save changes
            await _roomRepository.AddAsync(room);
            return room.Id;
        }
    }
}
