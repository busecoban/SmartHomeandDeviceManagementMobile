using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Rooms.Commands.DeleteRoomById
{
    public class DeleteRoomByIdCommand : IRequest<Response<int>>
    {
        public int Id { get; set; }
        public class DeleteRoomByIdCommandHandler : IRequestHandler<DeleteRoomByIdCommand, Response<int>>
        {
            private readonly IRoomRepositoryAsync _roomRepository;
            public DeleteRoomByIdCommandHandler(IRoomRepositoryAsync roomRepository)
            {
                _roomRepository = roomRepository;
            }
            public async Task<Response<int>> Handle(DeleteRoomByIdCommand command, CancellationToken cancellationToken)
            {
                var room = await _roomRepository.GetByIdAsync(command.Id);
                if (room == null) throw new ApiException($"Room Not Found.");
                await _roomRepository.DeleteAsync(room);
                return new Response<int>(room.Id);
            }
        }
    }
}
