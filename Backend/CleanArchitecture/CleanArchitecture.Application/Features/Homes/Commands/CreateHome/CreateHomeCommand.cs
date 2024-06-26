using System;
using System.Threading;
using System.Threading.Tasks;
using AutoMapper;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Interfaces.Repositories;
using CleanArchitecture.Core.Wrappers;
using MediatR;

namespace CleanArchitecture.Core.Features.Homes.Commands.CreateHome
{
	public class CreateHomeCommand : IRequest<int>
    {
        public string Name { get; set; }
        public string Address { get; set; }
        public string OwnerID { get; set; }
    }

    public class CreateHomeCommandHandler : IRequestHandler<CreateHomeCommand, int>
    {
        private readonly IHomeRepositoryAsync _homeRepository;


        public CreateHomeCommandHandler(IHomeRepositoryAsync homeRepository)
        {
            _homeRepository = homeRepository;
        }

        public async Task<int> Handle(CreateHomeCommand request, CancellationToken cancellationToken)
        {
            var home = new Home
            {
                Name = request.Name,
                Address = request.Address,
                OwnerId = request.OwnerID
            };
            await _homeRepository.AddAsync(home);
            
            return home.Id;
        }
    }
}

