using System;
namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllDevices
{
	public class GetAllDeviceViewModel
	{
        public int Id { get; set; }
        public string Name { get; set; }
        public string Type { get; set; }
        public bool Status { get; set; }
        public int RoomId { get; set; }
    }
}

