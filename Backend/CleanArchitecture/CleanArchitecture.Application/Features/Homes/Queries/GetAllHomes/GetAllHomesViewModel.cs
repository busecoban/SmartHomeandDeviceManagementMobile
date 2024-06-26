using System;
using System.Collections.Generic;

namespace CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes
{
	public class GetAllHomesViewModel
	{
        public int Id { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        public List<string> RoomNames { get; set; } = new List<string>();
        

    }
}

