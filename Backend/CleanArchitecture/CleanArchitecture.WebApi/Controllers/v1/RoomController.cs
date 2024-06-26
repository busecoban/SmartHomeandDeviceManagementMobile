
using System.Threading.Tasks;
using CleanArchitecture.Core.Features.Homes.Commands.CreateHome;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomesByOwnerId;
using CleanArchitecture.Core.Features.Products.Commands.DeleteProductById;
using CleanArchitecture.Core.Features.Products.Commands.UpdateProduct;
using CleanArchitecture.Core.Features.Products.Queries.GetHomeById;
using CleanArchitecture.Core.Features.Room.Commands.UpdateRoom;
using CleanArchitecture.Core.Features.Rooms.Commands.CreateRoom;
using CleanArchitecture.Core.Features.Rooms.Commands.DeleteRoomById;
using CleanArchitecture.Core.Features.Rooms.Queries.GetAllRooms;
using CleanArchitecture.Core.Features.Rooms.Queries.GetAllRoomsByHomeId;
using CleanArchitecture.Core.Features.Rooms.Queries.GetRoomById;
using CleanArchitecture.Core.Wrappers;
using Microsoft.AspNetCore.Mvc;

namespace CleanArchitecture.WebApi.Controllers.v1
{
    [ApiVersion("1.0")]
    public class RoomController : BaseApiController
    {

        [HttpPost("AddRoom")]
        public async Task<IActionResult> Post([FromQuery] string name, int HomeId)
        {
            CreateRoomCommand command = new CreateRoomCommand
            {
                Name = name,
                HomeID = HomeId
            };
            return Ok(await Mediator.Send(command));


        }
        [HttpPut("UpdateRoom")]
        public async Task<IActionResult> Put(int id, UpdateRoomCommand command)
        {
            if (id != command.Id)
            {
                return BadRequest();
            }
            return Ok(await Mediator.Send(command));
        }
        [HttpDelete("DeleteRoom")]
        public async Task<IActionResult> Delete(int id)
        {
            return Ok(await Mediator.Send(new DeleteRoomByIdCommand { Id = id }));

        }
        [HttpGet("GetAllRooms")]
        public async Task<IActionResult> Get([FromQuery] GetAllRoomParameter parameter)
        {
            var query = new GetAllRoomsQuery
            {
                PageNumber = parameter.PageNumber,
                PageSize = parameter.PageSize
            };

            var result = await Mediator.Send(query);

            return Ok(result);
        }
        [HttpGet("GetAllRoomsByHomeId")]
        public async Task<IActionResult> Get([FromQuery] GetAllRoomsByHomeIdQuery parameter)
        {
            var query = new GetAllRoomsByHomeIdQuery
            {
                PageNumber = parameter.PageNumber,
                PageSize = parameter.PageSize,
                HomeId = parameter.HomeId
            };

            var result = await Mediator.Send(query);

            return Ok(result);
        }
        [HttpGet("GetRoomById")]
        public async Task<IActionResult> GetRoomById(int Id)
        {
            var result = await Mediator.Send(new GetRoomByIdQuery
            {
                Id = Id
            });

            return Ok(result);
        }
    }
}
