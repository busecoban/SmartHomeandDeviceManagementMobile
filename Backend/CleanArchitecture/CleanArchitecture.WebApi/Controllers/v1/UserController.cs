using System;
using MediatR;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using CleanArchitecture.Core.Features.Users.Commands.CreateUser;
using CleanArchitecture.Core.Features.Devices.Commands.UpdateUser;
using CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers;
using CleanArchitecture.Core.Features.Devices.Commands.DeleteUserById;
using CleanArchitecture.Core.Features.Devices.Queries.GetUserById;

namespace CleanArchitecture.WebApi.Controllers.v1
{
    [ApiVersion("1.0")]
    public class UserController : BaseApiController
    {
        [HttpPost("AddRoommate")]
        public async Task<IActionResult> Post([FromQuery] string name, string Email, string Password, int HomeID)
        {
            CreateUserCommand command = new CreateUserCommand
            {
                Name = name,
                Email = Email,
                Password = Password,
                HomeId = HomeID
            };
            return Ok(await Mediator.Send(command));


        }
        [HttpPut("UpdateRoommate")]
        public async Task<IActionResult> Put(int id, UpdateUserCommand command)
        {
            if (id != command.Id)
            {
                return BadRequest();
            }
            return Ok(await Mediator.Send(command));
        }
        [HttpDelete("DeleteRoommate")]
        public async Task<IActionResult> Delete(int id)
        {
            return Ok(await Mediator.Send(new DeleteUserByIdCommand { Id = id }));

        }
        [HttpGet("GetAllRoommate")]
        public async Task<IActionResult> Get([FromQuery] GetAllUserParameter parameter)
        {
            var query = new GetAllUsersQuery
            {
                PageNumber = parameter.PageNumber,
                PageSize = parameter.PageSize
            };

            var result = await Mediator.Send(query);

            return Ok(result);
        }
        [HttpGet("GetRoommateById")]
        public async Task<IActionResult> GetUserById(int Id)
        {
            var result = await Mediator.Send(new GetUserByIdQuery
            {
                Id = Id
            });

            return Ok(result);
        }
    }
}

