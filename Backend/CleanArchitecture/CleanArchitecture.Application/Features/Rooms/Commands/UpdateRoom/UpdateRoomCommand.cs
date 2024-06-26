using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Room.Commands.UpdateRoom
{
    public class UpdateRoomCommand : IRequest<Response<int>>
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }
    public class UpdateRoomCommandHandler : IRequestHandler<UpdateRoomCommand, Response<int>>
    {
        private readonly IRoomRepositoryAsync _roomRepository;
        public UpdateRoomCommandHandler(IRoomRepositoryAsync roomRepository)
        {
            _roomRepository = roomRepository;
        }
        public async Task<Response<int>> Handle(UpdateRoomCommand command, CancellationToken cancellationToken)
        {
            var room = await _roomRepository.GetByIdAsync(command.Id);

            if (room == null) throw new EntityNotFoundException("room", command.Id);

            room.Name = command.Name;

            await _roomRepository.UpdateAsync(room);
            return new Response<int>(room.Id);
        }
    }

}
