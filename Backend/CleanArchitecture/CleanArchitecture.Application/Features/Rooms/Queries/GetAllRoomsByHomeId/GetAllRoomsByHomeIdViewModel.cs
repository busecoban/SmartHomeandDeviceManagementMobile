using System;
using CleanArchitecture.Core.Entities;
using System.Collections.Generic;

namespace CleanArchitecture.Core.Features.Rooms.Queries.GetAllRoomsByHomeId
{
	public class GetAllRoomsByHomeIdViewModel
	{
        public int Id { get; set; }
        public string Name { get; set; }
        public int HomeID { get; set; }
        public ICollection<string> Devices { get; set; } = new List<string>();
    }
}

