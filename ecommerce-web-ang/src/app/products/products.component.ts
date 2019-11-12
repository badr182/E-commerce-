import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../catalogue.service';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  private products
  editPhoto: boolean;
  currentProduct: any;
  selectedFiles: any;
  progress: any;
  currentFileUpload: any;
  private title: string

  constructor(
    private catService:CatalogueService,
    private route:ActivatedRoute, // ???
    private router:Router 
    ) {}

  
  ngOnInit() {
    this.router.events.subscribe((val) => {

      if( val instanceof NavigationEnd){
        let url = val.url
        let p1 = this.route.snapshot.params.p1
        if ( p1 == 1){
          this.title ="Selection"
          this.getProducts("/products/search/selectedProducts")
        }else if( p1 == 2 ) {
          
          let idCat = this.route.snapshot.params.p2
          this.title ="Produit Category "+ idCat
          this.getProducts("/categories/"+idCat+"/products")
        }else if( p1 == 3 ) {
          
          this.title ="Produit Promo "
          this.getProducts("/products/search/promoProducts")
        }else if( p1 == 4 ) {
          this.title ="Produit Dispo "
          this.getProducts("/products/search/dispoProducts")
        }else if( p1 == 5 ) {

          let idCat = this.route.snapshot.params.p2        
        }

    }
  
  })

    let p1 = this.route.snapshot.params.p1
        if ( p1 == 1){
          this.getProducts("/products/search/selectedProducts")
    }
  }

  private getProducts(url){
    this.catService.getResource(url)
    .subscribe( data => {
      this.products = data 
    },err => {
      console.log(err);
    })
  }

  onEditPhoto(p){
    this.currentProduct = p
    this.editPhoto = true ;
  }

  onSelectedFile( event ){
    // contient  ensemble des fichier selectionner 
    this.selectedFiles = event.target.files
  }

  uploadPhoto(){
    this.progress = 0
    this.currentFileUpload = this.selectedFiles.item(0)

    this.catService.uploadPhotoProduct( this.currentFileUpload, this.currentProduct.id ).subscribe(event =>{

      if( event.type === HttpEventType.UploadProgress ){

        this.progress = Math.round(100 *event.loaded / event.total)
        console.log(this.progress)
      }else if( event instanceof HttpResponse ){
        alert(" Download Finish")
        this.getProducts("/products/search/selectedProducts")
      }
    },err => {
      alert(" Issues during upload ")
    })
  }

}
