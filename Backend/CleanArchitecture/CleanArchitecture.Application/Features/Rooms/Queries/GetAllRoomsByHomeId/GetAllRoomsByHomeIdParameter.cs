using System;
using CleanArchitecture.Core.Filters;

namespace CleanArchitecture.Core.Features.Rooms.Queries.GetAllRoomsByHomeId
{
	public class GetAllRoomsByHomeIdParameter : RequestParameter
	{
		public int HomeId { get; set; }
	}
}

