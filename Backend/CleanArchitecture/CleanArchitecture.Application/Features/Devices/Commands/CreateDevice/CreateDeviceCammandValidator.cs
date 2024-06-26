
using CleanArchitecture.Core.Features.Devices.Commands.CreateUser;
using CleanArchitecture.Core.Interfaces.Repositories;
using FluentValidation;

namespace CleanArchitecture.Core.Features.Devices.Commands.CreateDevice
{
    public class CreateDeviceCommandValidator : AbstractValidator<CreateDeviceCommand>
    {
        private readonly IDeviceRepositoryAsync deviceRepository;
        public CreateDeviceCommandValidator(IDeviceRepositoryAsync deviceRepository)
        {
            this.deviceRepository = deviceRepository;

            RuleFor(p => p.Name)
                .NotEmpty().WithMessage("{PropertyName} is required.")
                .NotNull()
                .MaximumLength(50).WithMessage("{PropertyName} must not exceed 50 characters.");
            RuleFor(p => p.Type)
                .NotEmpty().WithMessage("{PropertyType} is required.")
                .NotNull()
                .MaximumLength(50).WithMessage("{PropertyName} must not exceed 50 characters.");
           
        }
    }
}

