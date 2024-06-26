using System;
using System.Collections.Generic;

namespace CleanArchitecture.Core.Entities
{
	public class Room
	{
        public int Id { get; set; }
        public string Name { get; set; }
        public int HomeID { get; set; }
        public Home Home { get; set; }
        public ICollection<Device> Devices { get; set; } = new List<Device>();
    }
}

