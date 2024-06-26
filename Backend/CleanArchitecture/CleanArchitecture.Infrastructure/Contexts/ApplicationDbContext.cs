using CleanArchitecture.Core.Interfaces;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Infrastructure.Models;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System.Linq;

namespace CleanArchitecture.Infrastructure.Contexts
{
    public class ApplicationDbContext : IdentityDbContext<ApplicationUser>
    {
        private readonly IDateTimeService _dateTime;
        private readonly IAuthenticatedUserService _authenticatedUser;

        public DbSet<Home> Homes { get; set; }
        public DbSet<Room> Rooms { get; set; }
        public DbSet<Device> Devices { get; set; }
        public DbSet<User> Users { get; set; }

        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options, IDateTimeService dateTime, IAuthenticatedUserService authenticatedUser) : base(options)
        {
            ChangeTracker.QueryTrackingBehavior = QueryTrackingBehavior.NoTracking;
            _dateTime = dateTime;
            _authenticatedUser = authenticatedUser;
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            // Configure many-to-many relationship between Home and User
            modelBuilder.Entity<Home>()
                .HasMany(h => h.Rooms)
                .WithOne(r => r.Home)
                .HasForeignKey(r => r.HomeID)
                .OnDelete(DeleteBehavior.Cascade); // If a home is deleted, rooms are also deleted

            // Configure Room-Device relationship
            modelBuilder.Entity<Room>()
                .HasMany(r => r.Devices)
                .WithOne(d => d.Room)
                .HasForeignKey(d => d.RoomID)
                .OnDelete(DeleteBehavior.Cascade); // If a room is deleted, devices are also deleted

            // Configure User-Home relationship
            modelBuilder.Entity<User>()
                .HasMany(u => u.Homes)
                .WithMany(h => h.Users)
                .UsingEntity(j => j.ToTable("HomeOwners")); // Using a join table

            base.OnModelCreating(modelBuilder);
        }
    }
}