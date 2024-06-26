using CleanArchitecture.Core.Features.Homes.Commands.CreateHome;
using CleanArchitecture.Core.Features.Rooms.Commands.CreateRoom;
using CleanArchitecture.Core.Interfaces.Repositories;
using FluentValidation;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Products.Commands.CreateProduct
{
    public class CreateHomeCommandValidator : AbstractValidator<CreateHomeCommand>
    {
        private readonly IHomeRepositoryAsync homeRepository;

        public CreateHomeCommandValidator(IHomeRepositoryAsync homeRepository)
        {
            this.homeRepository = homeRepository;

            RuleFor(p => p.Address)
                .NotEmpty().WithMessage("{Address} is required.")
                .NotNull()
                .MaximumLength(50).WithMessage("{Address} must not exceed 50 characters.");

            RuleFor(p => p.Name)
                .NotEmpty().WithMessage("{PropertyName} is required.")
                .NotNull()
                .MaximumLength(50).WithMessage("{PropertyName} must not exceed 50 characters.");

        }

        
    }
}
//Oluşturuken gereken şeyler.