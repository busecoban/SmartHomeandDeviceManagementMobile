using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using CleanArchitecture.Core.Features.Devices.Commands.CreateUser;
using CleanArchitecture.Core.Features.Devices.Commands.UpdateDevice;
using CleanArchitecture.Core.Features.Devices.Commands.DeleteDeviceById;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllDevices;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllDeviceById;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllDevicesByRoomId;

namespace CleanArchitecture.WebApi.Controllers.v1
{
	 [ApiVersion("1.0")]
        public class DeviceController : BaseApiController
        {

            [HttpPost("AddDevice")]
            public async Task<IActionResult> Post([FromQuery] string name, string type, int RoomId)
            {
                CreateDeviceCommand command = new CreateDeviceCommand
                {
                    Name = name,
                    Type = type,
                    Status = false,
                    RoomId = RoomId
                };
                return Ok(await Mediator.Send(command));


            }
            [HttpPut("UpdateDevice")]
            public async Task<IActionResult> Put([FromQuery] int id, UpdateDeviceCommand command)
            {
                if (id != command.Id)
                {
                    return BadRequest();
                }

                return Ok(await Mediator.Send(command));
            }
            [HttpDelete("DeleteDevice")]
            public async Task<IActionResult> Delete(int id)
            {
                return Ok(await Mediator.Send(new DeleteDeviceByIdCommand { Id = id }));

            }
            [HttpGet("GetAllDevices")]
            public async Task<IActionResult> Get([FromQuery] GetAllDevicesQuery parameter)
            {
                var query = new GetAllDevicesQuery
                {
                    PageNumber = parameter.PageNumber,
                    PageSize = parameter.PageSize
                };

                var result = await Mediator.Send(query);

                return Ok(result);
            }
        [HttpGet("GetAllDevicesByRoomId")]
        public async Task<IActionResult> Get([FromQuery] GetAllDevicesByRoomIdQuery parameter)
        {
            var query = new GetAllDevicesByRoomIdQuery
            {
                PageNumber = parameter.PageNumber,
                PageSize = parameter.PageSize,
                RoomId = parameter.RoomId
            };

            var result = await Mediator.Send(query);

            return Ok(result);
        }
        [HttpGet("GetDeviceById")]
            public async Task<IActionResult> GetHomeById([FromQuery] int Id)
            {
                var result = await Mediator.Send(new GetDeviceByIdQuery
                {
                    Id = Id
                });

                return Ok(result);
            }
        }
    }



