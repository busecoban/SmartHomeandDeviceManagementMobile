using AutoMapper;
using CleanArchitecture.Core.Entities;
using CleanArchitecture.Core.Features.Homes.Commands.CreateHome;
using CleanArchitecture.Core.Features.Products.Commands.CreateProduct;
using CleanArchitecture.Core.Features.Products.Queries.GetAllProducts;
using CleanArchitecture.Core.Features.Rooms.Commands.CreateRoom;

namespace CleanArchitecture.Core.Mappings
{
    public class GeneralProfile : Profile
    {
        public GeneralProfile()
        {
            /*  CreateMap<Product, GetAllProductsViewModel>().ReverseMap();
                 CreateMap<Category, CreateCategoryCommand>().ReverseMap();
                 CreateMap<CreateProductCommand, Product>();
                 CreateMap<GetAllProductsQuery, GetAllProductsParameter>();
            */
            CreateMap<Home, CreateRoomCommand>().ReverseMap();
            CreateMap<Room, CreateHomeCommand>().ReverseMap();

        }
    }
}
