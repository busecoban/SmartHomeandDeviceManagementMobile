using CleanArchitecture.Core.Exceptions;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Products.Commands.DeleteProductById
{
    public class DeleteHomeByIdCommand : IRequest<Response<int>>
    {
         public int Id { get; set; }
         public class DeleteHomeByIdCommandHandler : IRequestHandler<DeleteHomeByIdCommand, Response<int>>
         {
             private readonly IHomeRepositoryAsync _homeRepository;
             public DeleteHomeByIdCommandHandler(IHomeRepositoryAsync productRepository)
             {
                 _homeRepository = productRepository;
             }
             public async Task<Response<int>> Handle(DeleteHomeByIdCommand command, CancellationToken cancellationToken)
             {
                 var home = await _homeRepository.GetByIdAsync(command.Id);
                 if (home == null) throw new ApiException($"Home Not Found.");
                 await _homeRepository.DeleteAsync(home);
                 return new Response<int>(home.Id);
             }
         }
    }
}
