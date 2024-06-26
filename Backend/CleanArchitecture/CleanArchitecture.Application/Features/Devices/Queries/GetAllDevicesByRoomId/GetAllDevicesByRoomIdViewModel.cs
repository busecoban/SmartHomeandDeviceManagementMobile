using System;
namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllDevicesByRoomId
{
	public class GetAllDevicesByRoomIdViewModel
	{
        public int Id { get; set; }
        public string Name { get; set; }
        public string Type { get; set; }
        public int RoomID { get; set; }
        public bool Status { get; set; }
    }
}

