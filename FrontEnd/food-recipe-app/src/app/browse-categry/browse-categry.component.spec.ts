import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseCategryComponent } from './browse-categry.component';

describe('BrowseCategryComponent', () => {
  let component: BrowseCategryComponent;
  let fixture: ComponentFixture<BrowseCategryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrowseCategryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseCategryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
