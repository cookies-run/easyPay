import { AnimationEntryMetadata, state } from '@angular/core';
import { trigger, transition, animate, style, query, group } from '@angular/animations';

  export const routeHomeAnimation: AnimationEntryMetadata =
    trigger('routeAnimation', [
      transition('* <=> *', [
          query(':enter, :leave', style({ position: 'fixed', width:'100%',height:'100%' })
        , { optional: true }),
           group([
       query(':enter', [
          style({ transform: 'translateX(0%)' }),
          animate('0.5s ease-in-out', style({ transform: 'translateX(0%)' }))
        ], { optional: true }),
        query(':leave', [
          style({ transform: 'translateX(0%)' }),
          animate('0.5s ease-in-out', style({ transform: 'translateX(0%)'}))
        ], { optional: true })
     ])
    ])
    ]);
