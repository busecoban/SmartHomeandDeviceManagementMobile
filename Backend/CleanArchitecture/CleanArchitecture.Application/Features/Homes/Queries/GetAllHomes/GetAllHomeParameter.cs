using System;
using CleanArchitecture.Core.Filters;

namespace CleanArchitecture.Core.Features.Homes.Queries.GetAllHomes
{
	public class GetAllHomeParameter : RequestParameter
    {
        public int OwnerId { get; set; }
    }
}

