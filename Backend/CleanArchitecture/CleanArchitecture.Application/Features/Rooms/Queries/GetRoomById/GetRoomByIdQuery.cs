using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using CleanArchitecture.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Rooms.Queries.GetRoomById
{
     public class GetRoomByIdQuery : IRequest<Response<Entities.Room>>
     {
         public int Id { get; set; }
         public class GetRoomByIdQueryHandler : IRequestHandler<GetRoomByIdQuery, Response<Entities.Room>>
         {
             private readonly IRoomRepositoryAsync _roomRepository;
             public GetRoomByIdQueryHandler(IRoomRepositoryAsync roomRepository)
             {
                 _roomRepository = roomRepository;
             }
             public async Task<Response<Entities.Room>> Handle(GetRoomByIdQuery query, CancellationToken cancellationToken)
             {
                 var room = await _roomRepository.GetByIdAsync(query.Id);
                 if (room == null) throw new ApiException($"Room Not Found.");
                 return new Response<Entities.Room>(room);
             }
         }
     }
}
