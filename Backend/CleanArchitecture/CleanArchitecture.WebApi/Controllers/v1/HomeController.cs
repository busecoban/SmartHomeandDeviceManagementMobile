
using System.Threading.Tasks;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Homes.Commands.CreateHome;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes;
using CleanArchitecture.Core.Features.Homes.Queries.GetAllHomesByOwnerId;
using CleanArchitecture.Core.Features.Products.Commands.DeleteProductById;
using CleanArchitecture.Core.Features.Products.Commands.UpdateProduct;
using CleanArchitecture.Core.Features.Products.Queries.GetHomeById;
using Microsoft.AspNetCore.Mvc;

namespace CleanArchitecture.WebApi.Controllers.v1
{
    [ApiVersion("1.0")]
    public class HomeController : BaseApiController
    {
        
        [HttpPost("AddHome")]
        public async Task<IActionResult> Post([FromQuery] string name,string address, string ownerID)
        {
            CreateHomeCommand command = new CreateHomeCommand
            {
                Name = name,
                Address = address,
                OwnerID = ownerID
                
            };
            return Ok(await Mediator.Send(command));

            
        }
        [HttpPut("UpdateHome")]
        public async Task<IActionResult> Put([FromQuery] int id, UpdateHomeCommand command)
        {
            if (id != command.Id)
            {
                return BadRequest();
            }
            return Ok(await Mediator.Send(command));
        }
        [HttpDelete("DeleteHome")]
        public async Task<IActionResult> Delete(int id)
        {
            return Ok(await Mediator.Send(new DeleteHomeByIdCommand { Id = id }));

        }
        [HttpGet("GetAllHomes")]
        public async Task<IActionResult> Get([FromQuery] GetAllHomesQuery parameter)
        {
            var query = new GetAllHomesQuery
            {
                PageNumber = parameter.PageNumber,
                PageSize = parameter.PageSize
            };

            var result = await Mediator.Send(query);

            return Ok(result);
        }
        [HttpGet("GetAllHomesByOwnerId")]
        public async Task<IActionResult> Get([FromQuery] GetAllHomesByOwnerIdQuery parameter)
        {
            var query = new GetAllHomesByOwnerIdQuery
            {
                PageNumber = parameter.PageNumber,
                PageSize = parameter.PageSize,
                OwnerId = parameter.OwnerId
            };

            var result = await Mediator.Send(query);

            return Ok(result);
        }
        [HttpGet("GetHomeById")]
        public async Task<IActionResult> GetHomeById([FromQuery] int Id)
        {
            var result = await Mediator.Send(new GetHomeByIdQuery
            {
                Id = Id
            });

            return Ok(result);
        }
    }
}
