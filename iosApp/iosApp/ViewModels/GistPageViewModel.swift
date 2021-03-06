//
//  GistPageViewModel.swift
//  iosApp
//
//  Created by kaleidot725 on 2021/07/16.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

class GistPageViewModel : ObservableObject {
    private let userName: String
    private let gistRepository: GistRepositoryNative
    private let coroutineScope: Kotlinx_coroutines_coreCoroutineScope
    
    @Published var state: UiState = UiState.loading
    @Published var gists : [GistItem] = []
    
    init(userName: String, gistRepository: GistRepositoryNative, coroutineScope: Kotlinx_coroutines_coreCoroutineScope) {
        self.userName = userName
        self.gistRepository = gistRepository
        self.coroutineScope = coroutineScope
    }
    
    func fetchGists() {
        self.state = UiState.loading
        gistRepository
            .getGists(userName: userName)
            .subscribe(
                scope: coroutineScope,
                onSuccess: { array in
                    self.gists = (array != nil) ? array!.compactMap { $0 as? GistItem } : []
                    self.state = UiState.success
                },
                onThrow: { throwable in
                    print(throwable.description())
                    self.gists = []
                    self.state = UiState.failed
                }
            )
    }
    
    enum UiState {
        case loading
        case success
        case failed
    }
}
