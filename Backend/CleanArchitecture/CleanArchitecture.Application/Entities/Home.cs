using System;
using System.Collections.Generic;

namespace CleanArchitecture.Core.Entities
{
    public class Home
    {
        public int Id { get; set; }
        public string OwnerId { get; set; }  
        public string Name { get; set; }
        public string Address { get; set; }
        public ICollection<Room> Rooms { get; set; } = new List<Room>();
        public ICollection<User> Users { get; set; } = new List<User>();

    }
}