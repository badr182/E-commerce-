import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
//import { url } from 'inspector';

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {
  [x: string]: any;

  public host:string = "http://localhost:8080"

  constructor( private http: HttpClient ) { }

  // return object Observable 
  public getResource(url: string){
    console.log(this.host+url);
    return this.http.get( this.host+url );
  }


  uploadPhotoProduct( file: File, idProduct ): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData()
    formdata.append('file',file );
    // if( this.jwToken == null ) this.loadToken()

    const req = new HttpRequest('POST', this.host+'/uploadPhoto/'+idProduct ,formdata,{
      reportProgress: true,
      responseType: 'text',
      // headers: new HttpHeaders({'Authorization': this.jwToken })
    })
    return this.http.request(req)
  }
}
