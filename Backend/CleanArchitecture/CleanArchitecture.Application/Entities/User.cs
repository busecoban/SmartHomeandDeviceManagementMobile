using System;
using System.Collections.Generic;

namespace CleanArchitecture.Core.Entities
{
    public  class User
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public int HomeID { get; set; }
        public ICollection<Home> Homes { get; set; } = new List<Home>();
    }
}