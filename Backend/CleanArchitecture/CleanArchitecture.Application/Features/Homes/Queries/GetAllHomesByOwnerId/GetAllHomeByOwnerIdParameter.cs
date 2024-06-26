using System;
using CleanArchitecture.Core.Filters;

namespace CleanArchitecture.Core.Features.Homes.Queries.GetAllHomesByOwnerId
{
	public class GetAllHomeByOwnerIdParameter : RequestParameter
	{
        public string OwnerId { get; set; }
    }
}

