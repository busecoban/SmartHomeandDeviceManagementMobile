using System;
using CleanArchitecture.Core.Entities;
using System.Collections.Generic;

namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllUsers
{
	public class GetAllUsersViewModel
	{
        public int Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public int HomeId { get; set; }
        public ICollection<Home> homes { get; set; } = new List<Home>();
    }
}

