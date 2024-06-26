using System;
using CleanArchitecture.Core.Features.Homes.Commands.CreateHome;
using CleanArchitecture.Core.Features.Users.Commands.CreateUser;
using CleanArchitecture.Core.Interfaces.Repositories;
using FluentValidation;

namespace CleanArchitecture.Core.Features.Devices.Commands.CreateUser
{
	public class CreateUserCommandValidator : AbstractValidator<CreateUserCommand>
    {
        private readonly IUserRepositoryAsync userRepository;
        public CreateUserCommandValidator(IUserRepositoryAsync userRepository)
		{
            this.userRepository = userRepository;

            RuleFor(p => p.Name)
                .NotEmpty().WithMessage("{PropertyName} is required.")
                .NotNull()
                .MaximumLength(50).WithMessage("{PropertyName} must not exceed 50 characters.");
            RuleFor(p => p.Password)
                .NotEmpty().WithMessage("{PropertyPassword} is required.")
                .NotNull()
                .MaximumLength(50).WithMessage("{PropertyName} must not exceed 50 characters.");
            RuleFor(p => p.Email)
                .NotEmpty().WithMessage("{PropertyEmail} is required.")
                .NotNull()
                .MaximumLength(50).WithMessage("{PropertyName} must not exceed 50 characters.");
        }
	}
}

