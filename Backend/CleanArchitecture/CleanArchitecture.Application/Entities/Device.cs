using System;

namespace CleanArchitecture.Core.Entities
{
    public  class Device
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Type { get; set; }
        public int RoomID { get; set; }
        public bool Status { get; set; }
        public Room Room { get; set; }

    }
}