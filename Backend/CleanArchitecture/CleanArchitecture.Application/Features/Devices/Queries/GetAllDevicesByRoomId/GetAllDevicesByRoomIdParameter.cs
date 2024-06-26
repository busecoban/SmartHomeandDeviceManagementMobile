using System;
using CleanArchitecture.Core.Filters;

namespace CleanArchitecture.Core.Features.Devices.Queries.GetAllDevicesByRoomId
{
	public class GetAllDevicesByRoomIdParameter : RequestParameter
	{
		public int RoomId { get; set; }
	}
}

