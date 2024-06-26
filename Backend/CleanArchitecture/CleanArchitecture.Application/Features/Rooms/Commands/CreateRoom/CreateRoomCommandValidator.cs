using CleanArchitecture.Core.Features.Homes.Commands.CreateHome;
using CleanArchitecture.Core.Interfaces.Repositories;
using FluentValidation;
using System.Threading;
using System.Threading.Tasks;

namespace CleanArchitecture.Core.Features.Rooms.Commands.CreateRoom
{
    public class CreateRoomCommandValidator : AbstractValidator<CreateRoomCommand>
    {
        private readonly IRoomRepositoryAsync roomRepository;

        public CreateRoomCommandValidator(IRoomRepositoryAsync roomRepository)
        {
            this.roomRepository = roomRepository;

            RuleFor(p => p.Name)
                .NotEmpty().WithMessage("{Description} is required.")
                .NotNull()
                .MaximumLength(100).WithMessage("{Description} must not exceed 100 characters.");

        }


    }
}