using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Products.Commands.UpdateProduct
{
    public class UpdateHomeCommand : IRequest<Response<int>>
    {
          public int Id { get; set; }
          public string Name { get; set; }
          public string Address { get; set; }
    }
    public class UpdateHomeCommandHandler : IRequestHandler<UpdateHomeCommand, Response<int>>
          {
              private readonly IHomeRepositoryAsync _homeRepository;
              public UpdateHomeCommandHandler(IHomeRepositoryAsync homeRepository)
              {
                  _homeRepository = homeRepository;
              }
              public async Task<Response<int>> Handle(UpdateHomeCommand command, CancellationToken cancellationToken)
              {
                  var home = await _homeRepository.GetByIdAsync(command.Id);

                  if (home == null) throw new EntityNotFoundException("home", command.Id);

                home.Address = command.Address;
                home.Name = command.Name;
                await _homeRepository.UpdateAsync(home);
                return new Response<int>(home.Id);
              }
          }
    
}
